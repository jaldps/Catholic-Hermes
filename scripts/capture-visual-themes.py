#!/usr/bin/env python3
"""Capture screenshot smoke checks for the three Sancta Fides visual identities.

Runs on a GitHub-hosted runner with a booted Android emulator and adb available.
The script interacts by visible Android text/accessibility nodes so it also checks
that the theme picker and save interaction remain reachable.
"""
from __future__ import annotations

import subprocess
import time
import xml.etree.ElementTree as ET
from pathlib import Path

PACKAGE = "com.aistudio.sanctafides.qvmrpx"
ACTIVITY = "com.aistudio.sanctafides.qvmrpx/com.example.MainActivity"
OUT = Path("visual-snapshots")
OUT.mkdir(parents=True, exist_ok=True)


def adb(*args: str, timeout: int = 30, check: bool = True, capture: bool = True):
    return subprocess.run(
        ["adb", *args],
        check=check,
        timeout=timeout,
        stdout=subprocess.PIPE if capture else None,
        stderr=subprocess.PIPE if capture else None,
    )


def wait(seconds: float = 1.25):
    time.sleep(seconds)


def hierarchy() -> ET.Element:
    adb("shell", "uiautomator", "dump", "/sdcard/window.xml", timeout=30)
    result = subprocess.run(
        ["adb", "shell", "cat", "/sdcard/window.xml"],
        check=True,
        timeout=15,
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
        text=True,
    )
    return ET.fromstring(result.stdout)


def tap_visible_text(candidates: list[str], label: str) -> None:
    # Re-dump several times to allow bottom sheets and transitions to settle.
    end = time.time() + 15
    last_nodes = []
    attempts = 0
    while time.time() < end:
        attempts += 1
        root = hierarchy()
        matches = []
        for node in root.iter():
            text = node.attrib.get("text", "").strip()
            desc = node.attrib.get("content-desc", "").strip()
            if text in candidates or desc in candidates:
                bounds = node.attrib.get("bounds", "")
                if not bounds:
                    continue
                try:
                    left_top, right_bottom = bounds.strip("[]").split("][")
                    left, top = [int(value) for value in left_top.split(",")]
                    right, bottom = [int(value) for value in right_bottom.split(",")]
                except (ValueError, IndexError):
                    continue
                matches.append((node.attrib.get("clickable") == "true", (right-left)*(bottom-top), (left+right)//2, (top+bottom)//2, text or desc))
        last_nodes = matches
        if matches:
            # Prefer an actual clickable node, then a precise/small text target.
            matches.sort(key=lambda item: (not item[0], item[1]))
            _, _, x, y, found = matches[0]
            print(f"Tap {label}: {found!r} at ({x}, {y})", flush=True)
            adb("shell", "input", "tap", str(x), str(y), timeout=10)
            wait(1.5)
            return
        # The settings sheet is taller than some emulator viewports. Scroll it to
        # expose the save action instead of repeatedly tapping an off-screen node.
        if label == "save preferences" and attempts % 2 == 0:
            adb("shell", "input", "swipe", "540", "2050", "540", "650", "350", timeout=10)
            wait(0.8)
        else:
            wait(0.6)
    raise RuntimeError(f"Could not find {label}. Candidates={candidates}; last={last_nodes}")


def screenshot(name: str) -> None:
    wait(2.5)
    path = OUT / f"{name}.png"
    result = subprocess.run(
        ["adb", "exec-out", "screencap", "-p"],
        check=True,
        timeout=30,
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
    )
    path.write_bytes(result.stdout)
    print(f"Captured {path} ({path.stat().st_size:,} bytes)", flush=True)


def open_customizer() -> None:
    tap_visible_text(["Customize visual identity", "Personalizar Tema y Apariencia"], "theme settings button")


def set_theme_and_mode(theme_names: list[str], mode_names: list[str], save_names: list[str], screenshot_name: str) -> None:
    open_customizer()
    tap_visible_text(theme_names, "visual identity")
    tap_visible_text(mode_names, "light/dark mode")
    tap_visible_text(save_names, "save preferences")
    wait(4.0)

    # Capture actual in-app layouts, not only the theme picker or home palette.
    tap_visible_text(["Home", "Inicio", "Início"], "Home destination")
    screenshot(f"{screenshot_name}-home")

    tap_visible_text(["Bible", "Biblia", "Bíblia"], "Bible destination")
    screenshot(f"{screenshot_name}-bible")

    tap_visible_text(["Pray", "Orar", "Rezar"], "Rosary destination")
    screenshot(f"{screenshot_name}-rosary")

    tap_visible_text(["More", "Más", "Mais"], "Explore destination")
    screenshot(f"{screenshot_name}-explore")

    tap_visible_text(["Home", "Inicio", "Início"], "Return to Home")


def main() -> None:
    adb("shell", "settings", "put", "system", "font_scale", "1.0", timeout=10)
    adb("shell", "am", "force-stop", PACKAGE, check=False, timeout=10)
    adb("shell", "am", "start", "-n", ACTIVITY, timeout=20)
    wait(10.0)

    # Test all three identities explicitly in both brightness modes.
    identities = [
        (
            ["Vatican · Ecclesial editorial", "Vaticano · Estilo editorial", "Vaticano · Editorial eclesial"],
            "vatican",
        ),
        (
            ["Apple · Human Interface"],
            "apple",
        ),
        (
            ["Android · Material 3 Expressive", "Android · Material 3"],
            "material",
        ),
    ]
    light_names = ["Light", "Claro"]
    dark_names = ["Dark", "Oscuro", "Escuro"]
    save_names = [
        "Apply & Save Preferences",
        "Aplicar y Guardar Preferencias",
        "Aplicar e Salvar Preferências",
    ]

    # First theme can appear with an older label in existing local data; normalize it
    # by selecting the new Vatican preset and an explicit light mode.
    for theme_names, prefix in identities:
        set_theme_and_mode(theme_names, light_names, save_names, f"{prefix}-light")
        set_theme_and_mode(theme_names, dark_names, save_names, f"{prefix}-dark")

    print("VISUAL_SMOKE_OK: six themed screenshots captured.", flush=True)


if __name__ == "__main__":
    main()

#!/usr/bin/env python3
"""Remove the stray space before closing punctuation in the bundled Bibles.

Input format: {"book": {"chapter": ["verse", ...]}} (same compact JSON as the
assets). Only spaces/tabs immediately before . , ; : ! ? and » are removed;
opening ¿ ¡ « are never touched. Verse counts and any other character stay
exactly as they are. Writes the files with the same formatting they had:
compact separators, ensure_ascii=False and no trailing newline.

    python3 tools/bibles/fix_punctuation_spacing.py [--dry-run]
"""
import argparse
import json
import pathlib
import re

ROOT = pathlib.Path(__file__).resolve().parents[2]
FILES = {
    "es": ROOT / "app/src/main/assets/bible/bible_es.json",
    "en": ROOT / "app/src/main/assets/bible/bible_en.json",
}
# Closing signs only. Opening Spanish signs are deliberately absent.
SPACED_CLOSING = re.compile(r"[ \t]+(?=[.,;:!?»])")


def fix_verse(text):
    return SPACED_CLOSING.sub("", text)


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--dry-run", action="store_true", help="report counts without writing")
    args = ap.parse_args()

    for lang, path in FILES.items():
        raw = path.read_text(encoding="utf-8")
        data = json.loads(raw)
        changed_verses = 0
        removed_spaces = 0
        per_book = {}
        for book, chapters in data.items():
            book_changed = 0
            for chapter, verses in chapters.items():
                for i, verse in enumerate(verses):
                    fixed = fix_verse(verse)
                    if fixed != verse:
                        book_changed += 1
                        removed_spaces += len(verse) - len(fixed)
                        verses[i] = fixed
            if book_changed:
                per_book[book] = book_changed
                changed_verses += book_changed
        print(f"{lang}: {changed_verses} verses, {removed_spaces} spaces removed")
        for book in sorted(per_book):
            print(f"  {book}: {per_book[book]}")
        if not args.dry_run:
            out = json.dumps(data, ensure_ascii=False, separators=(",", ":"))
            path.write_text(out, encoding="utf-8", newline="")
            reparsed = json.loads(path.read_text(encoding="utf-8"))
            assert sum(len(v) for c in reparsed.values() for v in c.values()) == \
                   sum(len(v) for c in data.values() for v in c.values())
            assert all(fix_verse(verse) == verse
                       for chapters in reparsed.values()
                       for verses in chapters.values()
                       for verse in verses)


if __name__ == "__main__":
    main()

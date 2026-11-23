# -*- coding: utf-8 -*-
"""One-off: swap ES/EN in L(...) calls whose first two args are inverted.
Uses the new validator's own language fingerprint so the decision is mechanical."""
import ast, importlib.util, pathlib, sys

TOOLS = pathlib.Path(__file__).resolve().parents[1] / "tools" / "validate_chaplets.py"
spec = importlib.util.spec_from_file_location("vc", TOOLS)
vc = importlib.util.module_from_spec(spec); spec.loader.exec_module(vc)

FILES = ["batch3a.py","batch3b.py","batch4a.py","batch4b.py","batch5.py","batch6.py",
         "batch1a.py","batch1b.py","batch2a.py","batch2b.py"]
here = pathlib.Path(__file__).resolve().parent
total = 0
for name in FILES:
    p = here/name
    tree = ast.parse(p.read_text(encoding="utf-8"))
    changed = 0
    for node in ast.walk(tree):
        if not (isinstance(node, ast.Call) and isinstance(node.func, ast.Name) and node.func.id == "L"):
            continue
        if len(node.args) < 3: continue
        a,b = node.args[0], node.args[1]
        if not (isinstance(a, ast.Constant) and isinstance(a.value,str) and
                isinstance(b, ast.Constant) and isinstance(b.value,str)): continue
        if vc.wrong_language(a.value,"es") == "en" or vc.wrong_language(b.value,"en") == "es":
            node.args[0], node.args[1] = b, a
            changed += 1
    if changed:
        p.write_text(ast.unparse(tree) + "\n", encoding="utf-8")
        total += changed
        print(name, changed)
print("swapped", total)

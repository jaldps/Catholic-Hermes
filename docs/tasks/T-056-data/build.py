# -*- coding: utf-8 -*-
"""Generate chaplet JSONs from the data modules named on the command line."""
import importlib, sys
from lib import write
mods = [importlib.import_module(m) for m in sys.argv[1:]]
write(mods)

"""Contact sheets and side-by-side comparisons for design review.

  py -3 tools/device/montage.py sheet out.png img1 img2 ...      grid, labelled by filename
  py -3 tools/device/montage.py pair  out.png left.png right.png [labelA labelB]
Every image is scaled to the same height so proportions can be judged fairly.
"""
import sys
from PIL import Image, ImageDraw

H = 1100

def load(path, h=H):
    im = Image.open(path).convert("RGB")
    return im.resize((max(1, round(im.width * h / im.height)), h), Image.LANCZOS)

def label(canvas, x, y, text):
    d = ImageDraw.Draw(canvas)
    d.rectangle([x, y, x + 8 * len(text) + 16, y + 26], fill=(20, 20, 20))
    d.text((x + 8, y + 7), text, fill=(255, 255, 255))

def sheet(out, paths, cols=4):
    ims = [load(p, 900) for p in paths]
    w = max(i.width for i in ims); gap = 24
    rows = (len(ims) + cols - 1) // cols
    c = Image.new("RGB", (cols * (w + gap) + gap, rows * (900 + gap + 30) + gap), (236, 236, 236))
    for n, (im, p) in enumerate(zip(ims, paths)):
        x = gap + (n % cols) * (w + gap); y = gap + (n // cols) * (900 + gap + 30)
        c.paste(im, (x, y + 30)); label(c, x, y, p.replace("\\", "/").split("/")[-1])
    c.save(out)

def pair(out, a, b, la="A", lb="B"):
    ia, ib = load(a), load(b); gap = 40
    c = Image.new("RGB", (ia.width + ib.width + 3 * gap, H + 2 * gap + 30), (236, 236, 236))
    c.paste(ia, (gap, gap + 30)); c.paste(ib, (2 * gap + ia.width, gap + 30))
    label(c, gap, gap - 4, la); label(c, 2 * gap + ia.width, gap - 4, lb)
    c.save(out)

if __name__ == "__main__":
    mode, out, *rest = sys.argv[1:]
    if mode == "sheet": sheet(out, rest)
    elif mode == "pair": pair(out, *rest)
    print(out)

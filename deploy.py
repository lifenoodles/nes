#!/usr/bin/env python

"""
Build script for the NES emulator, Maven is sort of shitty
for tasks like copying files etc. so we'll just use this instead.
"""

import os
import shutil
import subprocess
import sys

if __name__ == "__main__":
    # run the maven build
    maven_result = subprocess.call(["mvn", "package"], shell=True)
    if maven_result != 0:
        print("Error occured during build process, aborting.")
        sys.exit(1)

    # set up release directory and copy shit into it
    try:
        os.mkdir("release")
    except:
        pass

    shutil.copy(
        os.path.join(
            "target", "nes-0.1-SNAPSHOT.jar"),
        os.path.join("release", "nes-emulator.jar"))

    shutil.copy(
        os.path.join("scripts", "nes.sh"), "release")

    shutil.copytree(
        os.path.join(
            "target", "natives"),
        os.path.join("release", "natives"))

#!/usr/bin/env bash

java -Djava.library.path=$(dirname $0)/natives -jar $(dirname $0)/nes-emulator.jar

#!/usr/bin/env bash

Xvfb :1 -ac -screen 0 1280x1024x8;

exec "$@"
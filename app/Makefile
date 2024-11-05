.DEFAULT_GOAL := build-run

install:
	gradlew.bat install

run-dist:
		gradlew.bat clean
		gradlew.bat installDist

build:
	gradlew.bat build

run:
	gradlew.bat run

test:
	gradlew.bat test

report:
	gradlew.bat jacocoTestReport

lint:
	gradlew.bat checkstyleMain checkstyleTest

build-run: build run

.PHONY: build

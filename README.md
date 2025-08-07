# Squirrel Invaders - Go Port

This is a Go port of the classic Java applet game "Squirrel Invaders".

## Building and Running

This project uses Ebiten for the game engine. To avoid dependencies on C libraries (like OpenGL and X11 development headers), it is recommended to build and run the project using the `ebiten_purego` build tag.

Use the following command to run the game:

```bash
go run -tags ebiten_purego .
```

This will compile and run the game in pure Go mode.

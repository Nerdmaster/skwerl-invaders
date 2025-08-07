package main

import "github.com/hajimehoshi/ebiten/v2"

const (
	enemyWidth  = 32
	enemyHeight = 32
)

type Enemy struct {
	x, y  float64
	image *ebiten.Image
}

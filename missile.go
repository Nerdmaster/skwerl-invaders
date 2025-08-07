package main

import (
	"image/color"

	"github.com/hajimehoshi/ebiten/v2"
)

const (
	missileWidth  = 4
	missileHeight = 8
	missileSpeed  = 4
)

type Missile struct {
	x, y  float64
	image *ebiten.Image
}

func NewMissile(x, y float64) *Missile {
	img := ebiten.NewImage(missileWidth, missileHeight)
	img.Fill(color.White)
	return &Missile{
		x:     x,
		y:     y,
		image: img,
	}
}

func (m *Missile) Update() {
	m.y -= missileSpeed
}

func (m *Missile) Draw(screen *ebiten.Image) {
	op := &ebiten.DrawImageOptions{}
	op.GeoM.Translate(m.x, m.y)
	screen.DrawImage(m.image, op)
}

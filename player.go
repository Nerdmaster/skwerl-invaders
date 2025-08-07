package main

import (
	"image"
	_ "image/gif"
	"log"

	"github.com/hajimehoshi/ebiten/v2"
	"github.com/hajimehoshi/ebiten/v2/ebitenutil"
)

const (
	playerWidth  = 32
	playerHeight = 32
)

// Player represents the player character.
type Player struct {
	x, y           float64
	image          *ebiten.Image
	missileManager *MissileManager
	fireCooldown   int
}

const playerFireCooldown = 15 // 4 shots per second at 60tps

// NewPlayer creates a new Player.
func NewPlayer(mm *MissileManager) *Player {
	// Load the sprite sheet.
	img, _, err := ebitenutil.NewImageFromFile("assets/images/spritestrip.gif")
	if err != nil {
		log.Fatal(err)
	}

	// Extract the player sprite from the sheet.
	playerImg := img.SubImage(image.Rect(0, 0, playerWidth, playerHeight)).(*ebiten.Image)

	return &Player{
		x:              240, // Initial position
		y:              320,
		image:          playerImg,
		missileManager: mm,
	}
}

// Update updates the player's state.
func (p *Player) Update() {
	// Handle input for movement.
	if ebiten.IsKeyPressed(ebiten.KeyLeft) {
		p.x -= 2
	}
	if ebiten.IsKeyPressed(ebiten.KeyRight) {
		p.x += 2
	}

	if p.fireCooldown > 0 {
		p.fireCooldown--
	}
	if ebiten.IsKeyPressed(ebiten.KeySpace) && p.fireCooldown == 0 {
		p.missileManager.Shoot(p.x, p.y)
		p.fireCooldown = playerFireCooldown
	}
}

// Draw draws the player on the screen.
func (p *Player) Draw(screen *ebiten.Image) {
	op := &ebiten.DrawImageOptions{}
	op.GeoM.Translate(p.x, p.y)
	screen.DrawImage(p.image, op)
}

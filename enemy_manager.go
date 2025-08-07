package main

import (
	"image"
	"log"

	"github.com/hajimehoshi/ebiten/v2"
	"github.com/hajimehoshi/ebiten/v2/ebitenutil"
)

const (
	enemiesPerRow = 5
	enemyRows     = 4
	enemyXOffset  = 56
	enemyYOffset  = 32
	enemySpacingX = 39
	enemySpacingY = 20
)

type EnemyManager struct {
	enemies     [][]*Enemy
	spriteSheet *ebiten.Image
	offsetX     float64
	offsetY     float64
	speed       float64
	direction   float64
}

func NewEnemyManager() *EnemyManager {
	img, _, err := ebitenutil.NewImageFromFile("assets/images/enemystrip0.gif")
	if err != nil {
		log.Fatal(err)
	}

	em := &EnemyManager{
		spriteSheet: img,
		enemies:     make([][]*Enemy, enemyRows),
		speed:       1,
		direction:   1,
	}

	// For now, all enemies use the same sprite.
	enemyImg := img.SubImage(image.Rect(0, 0, enemyWidth, enemyHeight)).(*ebiten.Image)

	for r := 0; r < enemyRows; r++ {
		em.enemies[r] = make([]*Enemy, enemiesPerRow)
		for c := 0; c < enemiesPerRow; c++ {
			enemy := &Enemy{
				x:     float64(enemyXOffset + c*(enemyWidth+enemySpacingX)),
				y:     float64(enemyYOffset + r*(enemyHeight+enemySpacingY)),
				image: enemyImg,
			}
			em.enemies[r][c] = enemy
		}
	}

	return em
}

func (em *EnemyManager) Update() {
	em.offsetX += em.speed * em.direction

	if em.offsetX >= 110 && em.direction > 0 {
		em.direction = -1
	}
	if em.offsetX <= -30 && em.direction < 0 {
		em.direction = 1
	}
}

func (em *EnemyManager) Draw(screen *ebiten.Image) {
	for _, row := range em.enemies {
		for _, enemy := range row {
			if enemy != nil {
				op := &ebiten.DrawImageOptions{}
				op.GeoM.Translate(enemy.x+em.offsetX, enemy.y+em.offsetY)
				screen.DrawImage(enemy.image, op)
			}
		}
	}
}

func (em *EnemyManager) RemoveEnemy(row, col int) {
	em.enemies[row][col] = nil
}

package main

import "github.com/hajimehoshi/ebiten/v2"

type MissileManager struct {
	missiles []*Missile
}

func NewMissileManager() *MissileManager {
	return &MissileManager{
		missiles: []*Missile{},
	}
}

func (mm *MissileManager) Shoot(x, y float64) {
	// Adjust to fire from the center of the player
	missile := NewMissile(x+playerWidth/2-missileWidth/2, y)
	mm.missiles = append(mm.missiles, missile)
}

func (mm *MissileManager) Update(em *EnemyManager) {
	keptMissiles := []*Missile{}
	for _, m := range mm.missiles {
		m.Update()

		// Check if missile is off screen
		if m.y+missileHeight < 0 {
			continue // remove missile
		}

		// Check for collision with enemies
		hit := false
		for r, row := range em.enemies {
			for c, enemy := range row {
				if enemy != nil {
					// enemy absolute position
					ex := enemy.x + em.offsetX
					ey := enemy.y + em.offsetY
					if m.x < ex+enemyWidth &&
						m.x+missileWidth > ex &&
						m.y < ey+enemyHeight &&
						m.y+missileHeight > ey {
						// collision
						em.RemoveEnemy(r, c)
						hit = true
						break
					}
				}
			}
			if hit {
				break
			}
		}

		if !hit {
			keptMissiles = append(keptMissiles, m)
		}
	}
	mm.missiles = keptMissiles
}

func (mm *MissileManager) Draw(screen *ebiten.Image) {
	for _, m := range mm.missiles {
		m.Draw(screen)
	}
}

package main

import (
	"image/color"
	"log"

	"github.com/hajimehoshi/ebiten/v2"
)

const (
	screenWidth  = 512
	screenHeight = 384
)

type GameState int

const (
	StateIntroAnimation GameState = iota
	StateIntroStory
	StateGame
	StateGameOver
)

// Game implements ebiten.Game interface.
type Game struct {
	player              *Player
	enemyManager        *EnemyManager
	missileManager      *MissileManager
	gameState           GameState
	introAnimationState *IntroAnimationState
	introStoryState     *IntroStoryState // Will be implemented in the next step
}

func (g *Game) startGame() {
	g.missileManager = NewMissileManager()
	g.player = NewPlayer(g.missileManager)
	g.enemyManager = NewEnemyManager()
}

// Update proceeds the game state.
// Update is called every tick (1/60 [s] by default).
func (g *Game) Update() error {
	switch g.gameState {
	case StateIntroAnimation:
		g.introAnimationState.Update(g)
	case StateIntroStory:
		g.introStoryState.Update(g)
	case StateGame:
		g.player.Update()
		g.enemyManager.Update()
		g.missileManager.Update(g.enemyManager)
	}
	return nil
}

// Draw draws the game screen.
// Draw is called every frame (typically 1/60[s] for 60Hz display).
func (g *Game) Draw(screen *ebiten.Image) {
	screen.Fill(color.Black)
	switch g.gameState {
	case StateIntroAnimation:
		g.introAnimationState.Draw(screen)
	case StateIntroStory:
		g.introStoryState.Draw(screen)
	case StateGame:
		if g.player != nil {
			g.player.Draw(screen)
		}
		if g.enemyManager != nil {
			g.enemyManager.Draw(screen)
		}
		if g.missileManager != nil {
			g.missileManager.Draw(screen)
		}
	}
}

// Layout takes the outside size (e.g., the window size) and returns the (logical) screen size.
// If you don't have to adjust the screen size with the outside size, just return a fixed size.
func (g *Game) Layout(outsideWidth, outsideHeight int) (screenWidth, screenHeight int) {
	return screenWidth, screenHeight
}

func main() {
	game := &Game{
		gameState:           StateIntroAnimation,
		introAnimationState: NewIntroAnimationState(),
	}
	ebiten.SetWindowSize(screenWidth, screenHeight)
	ebiten.SetWindowTitle("Squirrel Invaders")
	if err := ebiten.RunGame(game); err != nil {
		log.Fatal(err)
	}
}

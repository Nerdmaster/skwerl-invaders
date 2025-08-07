package main

import (
	"image/color"
	"log"

	"github.com/hajimehoshi/ebiten/v2"
	"github.com/hajimehoshi/ebiten/v2/inpututil"
	"github.com/hajimehoshi/ebiten/v2/text"
	"golang.org/x/image/font/basicfont"
)

type GameState int

const (
	StateTitleScreen GameState = iota
	StateGame
	StateGameOver
)

// Game implements ebiten.Game interface.
type Game struct {
	player         *Player
	enemyManager   *EnemyManager
	missileManager *MissileManager
	gameState      GameState
}

func (g *Game) updateTitleScreen() {
	if inpututil.IsKeyJustPressed(ebiten.KeyEnter) {
		g.startGame()
		g.gameState = StateGame
	}
}

func (g *Game) drawTitleScreen(screen *ebiten.Image) {
	title := "Squirrel Invaders"
	instructions := "Press Enter to Start"

	titleWidth := len(title) * 7 // basicfont.Face7x13: width is 7
	instructionsWidth := len(instructions) * 7

	text.Draw(screen, title, basicfont.Face7x13, (512-titleWidth)/2, 150, color.White)
	text.Draw(screen, instructions, basicfont.Face7x13, (512-instructionsWidth)/2, 200, color.White)
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
	case StateTitleScreen:
		g.updateTitleScreen()
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
	case StateTitleScreen:
		g.drawTitleScreen(screen)
	case StateGame:
		if g.player != nil { // Add nil checks
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
	return 512, 384
}

func main() {
	game := &Game{
		gameState: StateTitleScreen,
	}
	ebiten.SetWindowSize(512, 384)
	ebiten.SetWindowTitle("Squirrel Invaders")
	if err := ebiten.RunGame(game); err != nil {
		log.Fatal(err)
	}
}

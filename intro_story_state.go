package main

import (
	"image"
	"image/color"
	"log"

	"github.com/hajimehoshi/ebiten/v2"
	"github.com/hajimehoshi/ebiten/v2/ebitenutil"
	"github.com/hajimehoshi/ebiten/v2/inpututil"
	"github.com/hajimehoshi/ebiten/v2/text"
	"golang.org/x/image/font/basicfont"
)

type IntroStoryState struct {
	stripImage *ebiten.Image
	storyText  []string
}

func NewIntroStoryState() *IntroStoryState {
	img, _, err := ebitenutil.NewImageFromFile("assets/images/strip.gif")
	if err != nil {
		log.Fatal(err)
	}

	s := &IntroStoryState{
		stripImage: img,
		storyText: []string{
			"It was a time of chaos, a time of war.  Acorn and nut reserves were depleted",
			"all over the galaxy.  Earthlings were forced to fight hordes of rabid extra-",
			"terrestrial squirrels in order to preserve their own rations.  Rappy, once a",
			"kind and generous squirrel, was unable to control his need for good acorns.",
			"Rappy assembled an army of squirrels and other beasts to fight back against the",
			"earthlings.  With his friends and their deadly arsenal of unripe green acorns,",
			"they were unstoppable.  Rumors tore apart nations.  Governments allied with the",
			"squirrels and began secret projects to create evil squirrel-human hybrids.  These",
			"unspeakable events brought you to investigate.",
			"After discovering the horrifying truth of the situation you've realized that you,",
			"and ONLY you, will be able to destroy the alien squirrels and reclaim earth...",
		},
	}
	return s
}

func (s *IntroStoryState) Update(g *Game) {
	if inpututil.IsKeyJustPressed(ebiten.KeyEnter) {
		g.startGame()
		g.gameState = StateGame
	}
}

func (s *IntroStoryState) Draw(screen *ebiten.Image) {
	// Draw the tiled background
	for i := 0; i < introTileCount; i++ {
		op := &ebiten.DrawImageOptions{}
		op.GeoM.Translate(0, float64(i*introTileHeight))
		sx := 0
		sy := i * introTileHeight // In the intro animation, tile index matched strip index
		srcRect := image.Rect(sx, sy, sx+screenWidth, sy+introTileHeight)
		screen.DrawImage(s.stripImage.SubImage(srcRect).(*ebiten.Image), op)
	}

	// Draw the story text
	y := 90
	for _, line := range s.storyText {
		// For basicfont, each character is 7 pixels wide.
		x := (screenWidth - (len(line) * 7)) / 2
		text.Draw(screen, line, basicfont.Face7x13, x, y, color.White)
		y += 20
	}

	// Draw the prompt
	prompt := "Press Enter to Start"
	promptX := (screenWidth - (len(prompt) * 7)) / 2
	text.Draw(screen, prompt, basicfont.Face7x13, promptX, screenHeight-40, color.White)
}

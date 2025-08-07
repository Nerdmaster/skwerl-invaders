package main

import (
	"image"
	"log"

	"github.com/hajimehoshi/ebiten/v2"
	"github.com/hajimehoshi/ebiten/v2/ebitenutil"
)

const (
	introTileHeight = 64
	introTileCount  = 6
)

type IntroAnimationState struct {
	frameCounter int
	stripImage   *ebiten.Image
	visibleTiles [introTileCount]int // value is tile index, -1 if not visible
}

func NewIntroAnimationState() *IntroAnimationState {
	img, _, err := ebitenutil.NewImageFromFile("assets/images/strip.gif")
	if err != nil {
		log.Fatal(err)
	}

	s := &IntroAnimationState{
		stripImage: img,
	}

	for i := range s.visibleTiles {
		s.visibleTiles[i] = -1
	}

	return s
}

func (s *IntroAnimationState) Update(g *Game) { // Pass game to update for state transition
	s.frameCounter++

	switch s.frameCounter {
	case 10:
		s.visibleTiles[0] = 0
	case 110:
		s.visibleTiles[4] = 4
	case 210:
		s.visibleTiles[2] = 2
	case 310:
		s.visibleTiles[1] = 1
	case 410:
		s.visibleTiles[3] = 3
	case 510:
		s.visibleTiles[5] = 5
	case 1000:
		// Transition to the next state
		g.introStoryState = NewIntroStoryState()
		g.gameState = StateIntroStory
	}
}

func (s *IntroAnimationState) Draw(screen *ebiten.Image) {
	for i, tileIndex := range s.visibleTiles {
		if tileIndex != -1 {
			op := &ebiten.DrawImageOptions{}
			// Destination position for this strip
			op.GeoM.Translate(0, float64(i*introTileHeight))

			// Source rectangle from the sprite sheet
			sx := 0
			sy := tileIndex * introTileHeight
			srcRect := image.Rect(sx, sy, sx+screenWidth, sy+introTileHeight)

			screen.DrawImage(s.stripImage.SubImage(srcRect).(*ebiten.Image), op)
		}
	}
}

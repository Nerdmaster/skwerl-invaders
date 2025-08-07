import asyncio
from playwright.async_api import async_playwright
import os

async def main():
    async with async_playwright() as p:
        browser = await p.chromium.launch(headless=True)
        page = await browser.new_page()

        page.on("console", lambda msg: print(f"CONSOLE: {msg.text}"))
        page.on("pageerror", lambda exc: print(f"PAGE ERROR: {exc}"))

        file_path = os.path.abspath('index.html')
        await page.goto(f'file://{file_path}')

        # Wait for the game to be in the INTRO state
        await page.wait_for_function("() => window.game && window.game.gameState === 'INTRO'")

        # Wait a moment for the intro text to start scrolling
        await page.wait_for_timeout(2000)

        await page.screenshot(path='jules-scratch/verification/intro_screen.png')

        # Test skipping the intro
        await page.keyboard.press('Enter')
        await page.wait_for_function("() => window.game && window.game.gameState === 'MAIN_MENU'")
        await page.wait_for_timeout(1000)
        await page.screenshot(path='jules-scratch/verification/main_menu_screen.png')

        await browser.close()

if __name__ == '__main__':
    asyncio.run(main())

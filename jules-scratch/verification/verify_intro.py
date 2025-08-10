from playwright.sync_api import sync_playwright

def run():
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        page = browser.new_page()
        page.goto("http://localhost:8000")
        # Wait for the WASM to load and animation to start
        page.wait_for_timeout(3000) # 3 seconds
        page.screenshot(path="jules-scratch/verification/intro_screen.png")
        browser.close()

run()

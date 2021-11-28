import Home from "../pages";
import { render, screen } from "@testing-library/react"

describe("tests the index 'Home' page", () => {

    beforeEach(() => {
        render(<Home/>)
    })
    it("tests the correct title is displayed", () => {
        expect(screen.getByText("StockChecker")).toBeInTheDocument();
    })
    it("tests the description is displayed", () => {
        expect(screen.getByText("Simply enter your Stock Symbol (Ticker), such as")).toBeInTheDocument()
    })
    it("tests the text field is present on the screen", () => {
        expect(screen.getByTestId("symbol-input-field")).toBeInTheDocument()
    })
})
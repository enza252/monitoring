import Home from "../pages";
import { render, screen } from "@testing-library/react"

describe("tests the index 'Home' page", () => {
    it("tests the text field is present on the screen", () => {
        render(<Home/>)
        expect(screen.getByTestId("symbol-input-field")).toBeInTheDocument()
    })
})
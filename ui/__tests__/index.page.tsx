import Home from "../pages";
import { render, screen } from "@testing-library/react"
import userEvent from "@testing-library/user-event"

describe("tests the index 'Home' page", () => {

    beforeEach(() => {
        render(<Home/>)
    })
    it("tests the correct title is displayed", () => {
        expect(screen.getByText("StockChecker")).toBeInTheDocument();
    })
    it("tests the text field is present on the screen", () => {
        expect(screen.getByTestId("query-symbol-input-field")).toBeInTheDocument()
    })
    it("tests the input field accepts text and renders it once entered", () => {
        const textFieldElement = screen.getByRole("textbox", { name: "query-symbol-input-field" })
        expect(textFieldElement).toHaveValue("")
        userEvent.type(textFieldElement, "BP")
        expect(textFieldElement).toHaveValue("BP");
    })
    it("tests the submit buttin is present on the screen", () => {
        expect(screen.getByRole("button", { name: "submit-query-button" })).toBeInTheDocument()
        expect(screen.getByText("Submit")).toBeInTheDocument()
    })
})
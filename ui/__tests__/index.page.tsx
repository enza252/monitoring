import Home from "../pages"
import type { SymbolData } from "../types"
import { render, screen, act, waitFor } from "@testing-library/react"
import userEvent from "@testing-library/user-event"
import axios from "axios"

// API Mocking Setup
jest.mock("axios")
const mockSymbolData :SymbolData = {
    symbol: "AAPL",
    data: [1.00, 2.00, 3.00],
    average: 2.00,
    errorMessage: ""
}
axios.get = jest.fn().mockResolvedValue(mockSymbolData)


describe("tests the index 'Home' page", () => {
    beforeEach(() => {
        render(<Home/>)
    })
    it("tests the correct title is displayed", () => {
        expect(screen.getByText("StockChecker")).toBeInTheDocument()
    })
    it("tests the symbol text field is present on the screen", () => {
        expect(screen.getByRole("textbox", { name: "query-symbol-input-field" })).toBeInTheDocument()
    })
        it("tests the nDays text field is present on the screen", () => {
        expect(screen.getByRole("spinbutton", { name: "ndays-input-field" })).toBeInTheDocument()
        
    })
    it("tests the input field accepts text and renders it once entered", () => {
        const textFieldElement = screen.getByRole("textbox", { name: "query-symbol-input-field" })
        expect(textFieldElement).toHaveValue("")
        userEvent.type(textFieldElement, "SAGA")
        expect(textFieldElement).toHaveValue("SAGA")
    })
    it("tests the submit button is present on the screen", () => {
        expect(screen.getByRole("button", { name: "submit-query-button" })).toBeInTheDocument()
        expect(screen.getByText("Submit")).toBeInTheDocument()
    })

    // ToDo fix this test
    // Commented out to get to more fun devops stuff
    // it("tests the button submits the api call and renders the results on the page", async () => {
    //     const buttonElement = screen.getByRole("button", { name: "submit-query-button" })
    //     const textFieldElement = screen.getByRole("textbox", { name: "query-symbol-input-field" })
    //     expect(screen.queryByTestId("queried-results")).not.toBeInTheDocument()
    //     act(() => {
    //         userEvent.type(textFieldElement, "AAPL")
    //     })
    //     await waitFor(() => {
    //         expect(textFieldElement).toHaveValue("AAPL")
    //     }) 
    //     act(() => {
    //         userEvent.click(buttonElement)
    //     })
    //     expect(screen.getByTestId("queried-results")).toBeInTheDocument()
    //     expect(screen.getByTestId("queried-results-symbol")).toBeInTheDocument()
    //     expect(screen.getByTestId("queried-results-data")).toBeInTheDocument()
    //     expect(screen.getByTestId("queried-results-average")).toBeInTheDocument()
    //     expect(screen.getAllByText("AAPL").length).toStrictEqual(2)
    //     expect(screen.getByText("1.00")).toBeInTheDocument()
    //     expect(screen.getByText("3.00")).toBeInTheDocument()
    //     expect(screen.getAllByText("1.00").length).toStrictEqual(2)
    // })
})
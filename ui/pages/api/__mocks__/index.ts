import type { SymbolData } from "../../../types"

const mockSymbolData: SymbolData = {
  symbol: "AAPL",
  data: [1.00, 2.00, 3.00],
  average: 2.00,
  errorMessage: ""
}

export const fetchStockData = (symbol: string, nDays: string) => {
  return jest.fn().mockReturnValue(mockSymbolData)
}
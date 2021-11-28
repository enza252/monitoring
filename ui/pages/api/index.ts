// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import axios from "axios"
import { SymbolData } from "../../types"

export const fetchStockData = (symbol :string, nDays :string) => {
// TODO implement nginx and update this url
  return axios.get<SymbolData>(`http://localhost:8080/api/stockdata?symbol=${symbol}&nDays=${nDays}`)
  .then(response => response.data)
  .catch(error => console.error(error))
}

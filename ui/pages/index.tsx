import type { NextPage } from "next"
import type { SymbolData } from "../types"

import { useState } from "react"
import Head from "next/head"
import { TextField, Input, Button, Grid } from "@material-ui/core"

import styles from "../styles/Home.module.css"
import { fetchStockData } from "./api"

const Home: NextPage = () => {
  const [querySymbol, setQuerySymbol] = useState<string>("")
  const [queryNDays, setQueryNDays] = useState<string>("")
  const [symbolData, setSymbolData] = useState<SymbolData | undefined>(undefined)
  const onSymbolFieldChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setQuerySymbol(event.target.value)
  }
  const onNDaysFieldChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setQueryNDays(event.target.value)
  }
  const onButtonClick = () => {
    fetchStockData(querySymbol, queryNDays)
    .then(response => {
      if (response) {
        setSymbolData(response)
      }
    })
  }
  return (
    <div className={styles.container}>
      <Head>
        <title>StockChecker</title>
        <meta name="description" content="A simple way to check the price of a stock for the last N days, with a calculated average" />
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main className={styles.main}>
        <h1 className={styles.title}>
          StockChecker
        </h1>

        <p className={styles.description}>
          Simply enter your Stock Symbol (Ticker), such as <a href="https://www.google.com/finance/quote/AMZN:NASDAQ">AMZN</a>, <a href="https://www.google.com/finance/quote/IBM:NYSE">IBM</a> or <a href="https://www.google.com/finance/quote/GOOGL  :NASDAQ">GOOGL</a>.
        </p>

        <div className={styles.grid}>
          <div className={styles.card}>
            <Grid container spacing={2} direction="column" alignItems="center">
              <Grid item xs={12}>
               <TextField required id="query-symbol-input-field" data-testid="query-symbol-input-field" inputProps={{ "aria-label": "query-symbol-input-field" }} value={querySymbol} onChange={onSymbolFieldChange} label="Symbol"/>
              </Grid>
              <Grid item xs={12}>
               <Input required type="number" id="ndays-input-field" data-testid="ndays-input-field" inputProps={{ "aria-label": "ndays-input-field" }} value={queryNDays} onChange={onNDaysFieldChange} label="Number of Days"/>
              </Grid>
              <Grid>
                <Button aria-label="submit-query-button" variant="contained" onClick={onButtonClick}>Submit</Button>
              </Grid>
            </Grid>
          </div>

          {symbolData && !symbolData.errorMessage ? <div className={styles.card} data-testid="queried-results">
            <Grid container spacing={2} direction="column" alignItems="center">
              <Grid item xs={12}>
               <p data-testid="queried-results-symbol">Symbol: {symbolData.symbol}</p>
              </Grid>
              <Grid item xs={12}>
               <p data-testid="queried-results-data">Closing Value for the last N Days: {symbolData.data.join(', ')}</p>
              </Grid>
              <Grid item xs={12}>
               <p data-testid="queried-results-average">Average: {symbolData.average}</p>
              </Grid>                            
            </Grid>
          </div> : null}
        </div>
      </main>
    </div>
  )
}

export default Home

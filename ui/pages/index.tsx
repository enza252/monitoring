import type { NextPage } from "next"
import { useState } from "react"
import Head from "next/head"
import { TextField, Button, Grid } from "@material-ui/core"

import styles from "../styles/Home.module.css"

const Home: NextPage = () => {
  const [querySymbol, setQuerySymbol] = useState("")
  const onFieldChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setQuerySymbol(event.target.value)
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
               <TextField required id="query-symbol-input-field" data-testid="query-symbol-input-field" inputProps={{ "aria-label": "query-symbol-input-field" }} value={querySymbol} onChange={onFieldChange}/>
              </Grid>
              <Grid>
                <Button aria-label="submit-query-button" variant="contained">Submit</Button>
              </Grid>
            </Grid>
          </div>
        </div>
      </main>
    </div>
  )
}

export default Home

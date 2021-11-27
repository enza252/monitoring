import type { NextPage } from 'next'
import Head from 'next/head'
import styles from '../styles/Home.module.css'


const Home: NextPage = () => {
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
            <p>Find in-depth information about Next.js features and API.</p>
          </div>
        </div>
      </main>
    </div>
  )
}

export default Home

import Head from 'next/head';
import { ThemeProvider } from 'styled-components';

import GlobalStyle from '../styles/GlobalStyles';
import theme from '../styles/theme';

// import('../mocks');

export default function MyApp({ Component, pageProps }) {
  return (
    <ThemeProvider theme={theme}>
      <Head>
        <title>Puzzle</title>
      </Head>
      <GlobalStyle />
      <Component {...pageProps} />
    </ThemeProvider>
  );
}

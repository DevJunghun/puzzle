import React from 'react';
import Head from 'next/head';
import Link from 'next/link';

function Next() {
  return (
    <React.Fragment>
      <Head>
        <title>Puzzle (보낸 편지함)</title>
      </Head>
      <div>
        <p>
          ⚡ Electron + Next.js ⚡ -
          <Link href="/home">
            <a>Go to home page</a>
          </Link>
        </p>
      </div>
    </React.Fragment>
  );
}

export default Next;

import electron from 'electron';
import React from 'react';
import Head from 'next/head';
import MailFiltering from '../components/mailBox/MailFiltering/MailFiltering';
import MailCategoryList from '../components/mailBox/MailCategory/MailCategoryList/MailCategoryList';
import MailItem from '../components/mailBox/MailItem/Mailtem';
import Link from 'next/link';
import Layout from '../components/layout/layout';
import styled from 'styled-components';

const ipcRenderer = electron.ipcRenderer || false;

function Home() {
  const [message, setMessage] = React.useState('no ipc message');

  const onClickWithIpc = () => {
    ipcRenderer.send('ping-pong', 'some data from ipcRenderer');
  };

  const onClickWithIpcSync = () => {
    const message = ipcRenderer.sendSync('ping-pong-sync', 'some data from ipcRenderer');
    setMessage(message);
  };

  // If we use ipcRenderer in this scope, we must check the instance exists
  if (ipcRenderer) {
    // In this scope, the webpack process is the client
  }

  React.useEffect(() => {
    // like componentDidMount()

    // register `ping-pong` event
    ipcRenderer.on('ping-pong', (event, data) => {
      setMessage(data);
    });

    return () => {
      // like componentWillUnmount()

      // unregister it
      ipcRenderer.removeAllListeners('ping-pong');
    };
  }, []);

  return (
    <Layout>
      <Head>
        <title>Puzzle (받은 메일함)</title>
      </Head>

      <MailCategoryNavBarContainer>
        <MailFiltering />
        <MailCategoryList />
      </MailCategoryNavBarContainer>
      <MailItem />
      <MailItem />
      <Link href="/signin">로그인</Link>
    </Layout>
  );
}

const MailCategoryNavBarContainer = styled.div`
  display: flex;
  gap: 24px;
  margin-top: 20px;

  align-items: center;
  padding-bottom: 10px;
  border-bottom: 1px solid #d9d9d9;

  padding-left: 15px;
`;

export default Home;

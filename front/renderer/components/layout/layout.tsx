import { useAtomValue } from 'jotai';
import styled, { css } from 'styled-components';
import { globalSideBarAtom } from '../../atoms/global';
import SideBar from './RightSideBar';
import MailHeader from '../mailBox/MailHeader/MailHeader';
import { LeftSideBar } from './LeftSideBar';

const Container = styled.div<{ isSideBarOpen: boolean }>`
  position: relative;
  padding-left: 25px;
  padding-right: 25px;
  padding-top: 26.5px;
  transition: 0.4s;

  ${({ isSideBarOpen }) =>
    isSideBarOpen &&
    css`
      margin-right: 338px;
    `}
`;

const Layout = ({ children }) => {
  const isSideBarOpen = useAtomValue(globalSideBarAtom);

  return (
    <Container isSideBarOpen={isSideBarOpen}>
      <MailHeader />
      <main>{children}</main>
      <LeftSideBar />
      <SideBar />
    </Container>
  );
};

export default Layout;

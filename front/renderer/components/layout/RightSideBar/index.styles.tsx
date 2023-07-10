import styled, { css } from 'styled-components';
import Icon from '../../@common/Icon/Icon';

export const Container = styled.div<{ isOpen: boolean }>`
  height: 100%; /* 100% Full-height */
  width: 0; /* 0 width - change this with JavaScript */
  position: fixed; /* Stay in place */
  z-index: 1; /* Stay on top */
  top: 0;
  right: 0;
  background-color: white; /* Black*/
  overflow-x: hidden; /* Disable horizontal scroll */
  transition: 0.4s; /* 0.5 second transition effect to slide in the sidebar */

  ${({ isOpen }) =>
    isOpen &&
    css`
      border-left: 1px solid #d9d9d9;
      width: 338px;
      transition: 0.4s;
    `}
`;

export const ContractSideBarIcon = styled.div`
  position: absolute;
  top: 25px;
  left: 8px;
`;

import styled, { css } from 'styled-components';

export const Container = styled.div<{ isOpen: boolean }>`
  height: 100vh; /* 100% Full-height */
  position: absolute; /* Stay in place */
  z-index: 0; /* Stay on top */
  top: 0;
  left: 0;
  background-color: white; /* Black*/
  transition: 0.4s; /* 0.5 second transition effect to slide in the sidebar */

  padding-left: 25px;
  padding-right: 16px;

  ${({ isOpen }) =>
    isOpen &&
    css`
      border-left: 1px solid #d9d9d9;
      width: 310px;
      transition: 0.4s;
      box-shadow: 3px 3px 28.52px -4px rgba(0, 0, 0, 0.2);
    `}
`;

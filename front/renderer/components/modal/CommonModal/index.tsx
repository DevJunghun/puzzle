import styled, { css } from 'styled-components';

export const Container = styled.div<{ width: string; height: string; top?: string; left?: string }>`
  width: ${({ width }) => `${width}`};
  height: ${({ height }) => `${height}`};

  display: flex;
  position: absolute;
  top: ${({ top }) => `${top}px`};

  flex-direction: column;
  justify-content: space-between;

  border-radius: 5px;
  background-color: ${({ theme }) => theme.colors.WHITE};

  box-shadow: 3px 3px 28.52px -4px rgba(0, 0, 0, 0.2);
  z-index: 100;
`;

export const DropdownItem = styled.div<{
  height: string;
  index?: 'first' | 'last';
  itemLength: number;
}>`
  color: ${({ theme }) => theme.colors.GRAY_008};
  padding: ${({ height, itemLength }) => `calc(${height} / ${itemLength * 4}) 0;`}
  cursor: pointer;
  padding-left: 1.3125rem;

  font-size: 0.875rem;
  font-weight: bold;

  :hover {
    backdrop-filter: brightness(0.9);
  }

  ${({ index }) =>
    index === 'last' &&
    css`
      border-bottom-right-radius: 5px;
      border-bottom-left-radius: 5px;
    `}

  ${({ index }) =>
    index === 'first' &&
    css`
      border-top-right-radius: 5px;
      border-top-left-radius: 5px;
    `}
`;

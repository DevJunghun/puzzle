import styled from 'styled-components';

export const Container = styled.span`
  display: inline-block;
  width: 3.3125rem;
  height: 1.8125rem;
  text-align: center;
  line-height: 1.8125rem;
  border-radius: 1.5rem;
  border-color: transparent;

  background-color: ${({ theme }) => theme.colors.GRAY_001};
  color: ${({ theme }) => theme.colors.GRAY_008};
  font-size: 0.875rem;

  cursor: pointer;

  :hover {
    filter: brightness(0.9);
  }
`;

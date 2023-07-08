import { ButtonHTMLAttributes, InputHTMLAttributes } from 'react';
import styled, { css } from 'styled-components';

type ButtonProps = {
  borderRadius: number;
  width: string;
  height: string;
  isPrimary: boolean;
} & ButtonHTMLAttributes<HTMLButtonElement>;

export const Button = ({ ...props }: ButtonProps) => {
  return <ButtonBox {...props}></ButtonBox>;
};

export const ButtonBox = styled.button<ButtonProps>`
  ${({ width, height, borderRadius }) => `
    width: ${width}px;
    height: ${height}px;
    border-radius: ${borderRadius}px;
    border-color: transparent;
  `}

  ${({ isPrimary, theme }) =>
    isPrimary
      ? css`
          color: white;
          background-color: ${theme.colors.BLUE_002};
        `
      : css`
          color: ${theme.colors.GRAY_008};
          background-color: transparent;
        `}

  cursor: pointer;
  font-size: 16px;
`;

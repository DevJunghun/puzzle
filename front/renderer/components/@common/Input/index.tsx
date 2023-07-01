import { InputHTMLAttributes } from 'react';
import styled from 'styled-components';

type InputProps = {
  mainColor: string;
  defaultColor: string;
  label?: string;
  borderRadius: number;
  fontSize: number;
  paddingLeft: number;
  labelGap: number;
} & InputHTMLAttributes<HTMLInputElement>;

export const Input = ({ label, type, ...props }: InputProps) => {
  return (
    <InputWrapper labelGap={props.labelGap}>
      <Label defaultColor={props.defaultColor}>{label}</Label>
      <InputBox {...props} type={type} />
    </InputWrapper>
  );
};

export const InputBox = styled.input<InputProps>`
  ${({ width, height, mainColor, borderRadius, defaultColor, fontSize, paddingLeft }) => `
    width: ${width}px;
    height: ${height}px;
    border-radius: ${borderRadius}px;
    border: 1px solid ${defaultColor};
    font-size: ${fontSize}px;
    padding-left: ${paddingLeft}px;
    outline: none;

    :focus {
      border: 2px solid ${mainColor};
    }
  `}
`;

export const Label = styled.label<{ defaultColor: string }>`
  ${({ defaultColor }) => `color: ${defaultColor}`}
`;

export const InputWrapper = styled.div<{ labelGap: number }>`
  display: flex;
  flex-direction: column;
  gap: ${({ labelGap }) => `${labelGap}px`};
`;

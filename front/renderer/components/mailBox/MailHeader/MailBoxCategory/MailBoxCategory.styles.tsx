import styled from 'styled-components';

export const ActiveBox = styled.span<{ isActive: boolean }>`
  font-weight: ${({ isActive }) => (isActive ? 'bold' : 'normal')};
  margin-left: 2.375rem;
  cursor: pointer;
`;

export const MailBoxCategoryText = styled.span`
  font-size: 1rem;
  color: ${({ theme }) => theme.colors.GRAY_008};
`;

export const MailCount = styled.span`
  color: ${({ theme }) => theme.colors.BLUE_001};
`;

export const MailBoxCategoryWrapper = styled.div`
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: 0.375rem;
`;

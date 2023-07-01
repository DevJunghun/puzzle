import * as S from './MailBoxCategory.styles';

interface MailBoxCategoryProps {
  text: string;
  count: number;
  isActive: boolean;
  onClick: () => void;
}

const MailBoxCategory = ({ text, count, isActive, onClick }: MailBoxCategoryProps) => {
  return (
    <S.ActiveBox isActive={isActive} onClick={onClick}>
      <S.MailBoxCategoryWrapper>
        <S.MailBoxCategoryText>{text}</S.MailBoxCategoryText>
        <S.MailCount>{count}</S.MailCount>
      </S.MailBoxCategoryWrapper>
    </S.ActiveBox>
  );
};

export default MailBoxCategory;

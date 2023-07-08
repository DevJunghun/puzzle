import * as S from './MailCategory.styles';

interface MailCategoryProps {
  text: string;
}

const MailCategory = ({ text }: MailCategoryProps) => {
  return <S.Container>{text}</S.Container>;
};

export default MailCategory;

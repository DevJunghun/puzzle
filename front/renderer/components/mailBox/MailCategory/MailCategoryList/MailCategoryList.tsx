import MailBoxCategory from '../../MailHeader/MailBoxCategory/MailBoxCategory';
import MailCategory from '../MailCategory';
import * as S from './MailCategoryList.styles';

const MailCategoryList = () => {
  return (
    <S.Container>
      <MailCategory text="업무" />
      <MailCategory text="광고" />
      <MailCategory text="개인" />
    </S.Container>
  );
};

export default MailCategoryList;

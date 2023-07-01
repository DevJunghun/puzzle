import { RecursiveFolder } from '../../@common/RecursiveFolder';
import * as S from './index.styles';

export const LeftSideBar = () => {
  return (
    <S.Container isOpen={true}>
      <RecursiveFolder
        mainIconUrl="/images/icon-store-mono.svg"
        mainText="메일 보관함"
        data={['황정민', '이준혁', '정한힘', '이시연', '이병윤', '신태선']}
      />
      <RecursiveFolder
        mainIconUrl="/images/icon-document-mono.svg"
        mainText="템플릿 보관함"
        data={['템플릿1', '템플릿2', '템플릿2', '템플릿3']}
      />
    </S.Container>
  );
};

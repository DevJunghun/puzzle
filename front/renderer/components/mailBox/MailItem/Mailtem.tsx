import styled from 'styled-components';
import CheckBox from '../../@common/CheckBox/CheckBox';
import Icon from '../../@common/Icon/Icon';

interface MailItemProps {
  isChecked: boolean;
  name: string;
  title: string;
  isOpened: boolean;
  content: string;
}

const MailItem = ({}) => {
  return (
    <Container>
      <CheckBox isChecked={false} />
      <Icon src="/images/icon-star-mono.svg" width={24} height={24} />
      <div>황정민</div>
      <Icon src="/images/icon-closed-mail.svg" width={24} height={24} />
      <div>메일 확인 부탁드립니다.</div>
      <MainContent>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Non fugiat fuga perspiciatis
        maiores voluptas cupiditate explicabo ea, similique dignissimos quia possimus laudantium nam
        facere earum repellendus aperiam dolores eligendi? Laboriosam.
      </MainContent>
      <IconWrapper>
        <Icon src="/images/icon-bin-mono.svg" width={24} height={24} />
        <Icon src="/images/icon-store-mono.svg" width={24} height={24} />
        <Icon src="/images/icon-clock.svg" width={24} height={24} />
      </IconWrapper>
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  align-items: center;
  color: ${({ theme }) => theme.colors.GRAY_008};

  padding-left: 15px;

  padding: 10px 0;

  > * {
    flex: 1;
  }

  > :first-child {
    flex: 0 0 3%;
  }
`;

const MainContent = styled.div`
  display: inline-block;
  white-space: normal;
  overflow: hidden;
  text-overflow: ellipsis;
  text-align: left;
  word-wrap: break-word;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  line-height: 1.5;
  height: 1.5rem;
`;

const IconWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: flex-end;
  gap: 30px;
`;

export default MailItem;

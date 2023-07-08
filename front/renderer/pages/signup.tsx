import styled from 'styled-components';
import { Button } from '../components/@common/Button';
import { Input } from '../components/@common/Input';
import theme from '../styles/theme';

const defaultInputStyle = {
  width: 348,
  height: 40,
  mainColor: theme.colors.BLUE_002,
  defaultColor: theme.colors.GRAY_008,
  borderRadius: 5,
  fontSize: 18,
  paddingLeft: 8,
  labelGap: 11,
};

const defaultButtonStyle = {
  borderRadius: 5,
  width: '86',
  height: '41',
};

const SignUp = () => {
  return (
    <Layout>
      <SignUpWrapper>
        <HeadText>회원가입</HeadText>
        <InputWrapper>
          <Input {...defaultInputStyle} type="text" label="아이디" />
          <Input {...defaultInputStyle} type="password" label="비밀번호" />
          <Input {...defaultInputStyle} type="password" label="비밀번호 확인" />
          <Input {...defaultInputStyle} type="text" label="이름" />
        </InputWrapper>
        <BottomLayerWrapper>
          <ButtonWrapper>
            <Button {...defaultButtonStyle} isPrimary={true}>
              계정추가
            </Button>
          </ButtonWrapper>
        </BottomLayerWrapper>
      </SignUpWrapper>
    </Layout>
  );
};

const HeadText = styled.h1`
  font-size: 20px;
  color: ${({ theme }) => theme.colors.GRAY_008};
`;

const InputWrapper = styled.div`
  display: flex;
  flex-direction: column;
  gap: 30px;
  margin-bottom: 33px;
  margin-top: 45px;
`;

const Layout = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100%;
`;

const SignUpWrapper = styled.div`
  margin-top: 45px;
`;

const BottomLayerWrapper = styled.div`
  display: flex;
  align-items: center;
`;

const ButtonWrapper = styled.div`
  margin-left: auto;
`;

export default SignUp;

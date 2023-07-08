import { rest } from 'msw';
import users from './dummy.json';

export const userHandlers = [
  rest.post<{ password: string; email: string }>('/authenticate/auth', async (req, res, ctx) => {
    await sleep(100);

    const { password, email } = req.body;

    const user = users.find((user) => user.email === email && user.pwd === password) as
      | (typeof users)[number]
      | undefined;

    if (user) {
      return res(
        ctx.status(200),
        ctx.json({
          userToken: 'dddwdwd',
        }),
      );
    }

    return res(ctx.status(400));
  }),
  rest.put<{ username: string; pwd: string; email: string }>(
    '/user/sign-in',
    async (req, res, ctx) => {
      await sleep(100);

      const { username, pwd, email } = req.body;

      const isDuplicatedEmail = users.find((user) => user.email === email);
      const isDuplicatedUserName = users.find((user) => user.username === username);
      const isInvalidPassword = !/^(?=.*[a-zA-Z])(?=.*[0-9]).{6,20}$/.test(pwd);
      const isInvalidUserName = username.length <= 20;

      if (isInvalidUserName) {
        throw new Error('유저 이름은 최대 20자까지입니다.');
      }

      if (isInvalidPassword) {
        throw new Error(
          '비밀번호는 영문과 숫자를 포함하는 최소 6글자에서 최대 20글자의 문자여야합니다.',
        );
      }

      if (isDuplicatedUserName) {
        throw new Error('이미 등록된 유저이름입니다.');
      }

      if (isDuplicatedUserName) {
        throw new Error('이미 등록된 유저이름입니다.');
      }

      if (isDuplicatedEmail) {
        throw new Error('이미 존재하는 이메일입니다.');
      }

      users.push({
        username,
        pwd,
        email,
      });

      return res(
        ctx.status(201),
        ctx.json({
          uuid: '2reibbuceeb',
        }),
      );
    },
  ),
];

async function sleep(timeout: number) {
  return new Promise((resolve) => {
    setTimeout(resolve, timeout);
  });
}

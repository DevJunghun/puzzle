import { setupServer } from 'msw/node';
import { userHandlers } from './user/handlers';

const server = setupServer(...userHandlers);
export default server;

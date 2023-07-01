import { setupWorker } from 'msw';
import { userHandlers } from './user/handlers';

export const worker = setupWorker(...userHandlers);

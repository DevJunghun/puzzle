import { atom } from 'jotai';

type MaiLBoxCategoryStatusAtomValues = 'send' | 'receive' | 'important' | 'trash';

export const mailBoxCategoryStatusAtom = atom<MaiLBoxCategoryStatusAtomValues>('send');

export const isActiveMailFilteringAtom = atom(false);

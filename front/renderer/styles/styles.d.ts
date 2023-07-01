import theme from './theme';

type ThemeTyping = typeof theme;

declare module 'styled-components' {
  interface DefaultTheme extends ThemeTyping {}
}

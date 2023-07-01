const path = require('path');
const webpack = require('webpack');

module.exports = async ({ config }) => {
  config.module.rules
    .filter((rule) => rule.test.test('.svg'))
    .forEach((rule) => (rule.exclude = /\.svg$/i));

  // add SVGR instead
  config.module.rules.push({
    test: /\.svg$/,
    use: [
      {
        loader: '@svgr/webpack',
      },
      {
        loader: 'file-loader',
        options: {
          name: 'static/media/[path][name].[ext]',
        },
      },
    ],
    type: 'javascript/auto',
    issuer: {
      and: [/\.(ts|tsx|js|jsx|md|mdx)$/],
    },
  });

  return config;
};

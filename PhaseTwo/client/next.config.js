/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  // uncomment for testing
  // experimental: {
  //   swcPlugins: [
  //     ['swc-plugin-coverage-instrument', {}]
  //   ]
  // },
  images: {
    remotePatterns: [
      {
        protocol: 'https',
        hostname: 'picsum.photos',
        port: '',
        pathname: '/**',
      },
      {
        protocol: 'http',
        hostname: 'localhost',
        port: '8080',
        pathname: '/**',
      },
    ],
  },
}

module.exports = nextConfig

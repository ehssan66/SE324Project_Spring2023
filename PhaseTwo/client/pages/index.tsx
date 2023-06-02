import { Open_Sans } from 'next/font/google'
import Game from '@/components/Game'
import Layout from '../components/layouts'

const OpenSans = Open_Sans({ subsets: ['latin'], display: 'swap' })

export default function Index() {
  return (
    <Layout>
      <main className="flex flex-col items-center w-full h-full flex-grow max-w-6xl mx-auto overflow-hidden">
        <Game />
      </main>
    </Layout>
  )
}

import { Open_Sans } from 'next/font/google'
import InkyGame from '@/components/InkyGame'
import Layout from '../components/layouts'

const OpenSans = Open_Sans({ subsets: ['latin'], display: 'swap' })

export default function inky() {
  return (
    <Layout>
      <main className="flex flex-col items-center w-full h-full flex-grow max-w-6xl mx-auto overflow-hidden">
        <InkyGame />
      </main>
    </Layout>
  )
}
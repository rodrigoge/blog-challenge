import Posts from '@/pages/posts'
import '../styles/global.css'
import { Outfit } from 'next/font/google'
import Commentaries from '@/pages/commentaries'

const outfit = Outfit({
  weight: ['400', '700'],
  style: ['normal'],
  subsets: ['latin']
})

export const metadata = {
  title: 'Blog Challenge',
  description: 'A challenge from development a minimal blog using Clean Architecture and others skills.',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en" className={outfit.className}>
      <body className='
        bg-slate-100
        text-zinc-900
        flex
        justify-center
        items-center
        mt-12
      '>
        <div>
          <button className='
            bg-green-500
            text-slate-100
            hover:bg-green-600
            font-semibold
            p-3
            m-3
            w-40
            rounded
            flex
            items-center
            justify-center'>
            Create post
          </button>
          <Posts />
        </div>
      </body>
    </html>
  )
}
